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
    
    let background: UIColor?
    
    let children: [View]
    
    static let anchorDefault = Anchor.topFill
    static let originDefault = Vector.zero
    
    static func parse(bundle: Bundle, resource: String) throws -> View {
        let yaml = try Goo.load(bundle: bundle, resource: resource)
        return View(yaml)
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
        
        background = yaml["background"].color
        
        children = yaml["children"].arrayValue.map { View($0) }
    }
    
    
    
}
