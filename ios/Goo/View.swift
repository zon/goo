import Foundation
import Yaml

class View {
    let id: String?
    let anchor: Anchor
    let origin: Vector
    let layout: Layout
    let left: Double?
    let right: Double?
    let top: Double?
    let bottom: Double?
    let width: Double?
    let height: Double?
    
    static let anchorDefault = Anchor.topFill
    static let originDefault = Vector.zero
    
    static func parse(bundle: Bundle, resource: String) throws -> View {
        let text = try Goo.load(bundle: bundle, resource: resource)
        let yaml = try Yaml.load(text)
        return View(yaml)
    }
    
    init(
        id: String? = nil,
        anchor: Anchor = anchorDefault,
        origin: Vector = originDefault,
        layout: Layout = Layout(),
        left: Double? = nil,
        right: Double? = nil,
        top: Double? = nil,
        bottom: Double? = nil,
        width: Double? = nil,
        height: Double? = nil
    ) {
        self.id = id
        self.anchor = anchor
        self.origin = origin
        self.layout = layout
        self.left = left
        self.right = right
        self.top = top
        self.bottom = bottom
        self.width = width
        self.height = height
    }
    
    init(_ yaml: Yaml) {
        id = yaml["id"].string
        
        let anchorData = yaml["anchor"]
        if let key = anchorData.string {
            anchor = Anchor.dictionary[key] ?? View.anchorDefault
        } else {
            anchor = Anchor(anchorData) ?? View.anchorDefault
        }
        
        let originData = yaml["origin"]
        if originData.string == "center" {
            origin = Vector.half
        } else  {
            origin = Vector(originData) ?? View.originDefault
        }
        
        layout = Layout(yaml["layout"])
        
        left = yaml["left"].double ?? yaml["x"].double
        right = yaml["right"].double
        top = yaml["top"].double ?? yaml["y"].double
        bottom = yaml["bottom"].double
        width = yaml["width"].double
        height = yaml["height"].double
    }
    
}
