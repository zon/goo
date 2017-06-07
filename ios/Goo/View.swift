import Foundation
import Yaml

enum ViewType: String {
    case view = "view"
    case root = "root"
}

class View {
    let id: String?
    let type: ViewType
    let transform: Transform
    let layout: Layout
    
    let background: UIColor?
    
    weak var parent: View?
    var children: [View]
    
    static func parse(bundle: Bundle, resource: String) throws -> View {
        let yaml = try Goo.load(bundle: bundle, resource: resource)
        return View(yaml)
    }
    
    init(_ yaml: Yaml, transform: Transform? = nil) {
        id = yaml["id"].string
        type = yaml["type"].string.flatMap { t in ViewType(rawValue: t) } ?? .view
        self.transform = transform ?? Transform(yaml)
        layout = Layout(yaml)
        
        background = yaml["background"].color
        
        children = []
        children = yaml["children"].arrayValue.map { y in
            let child = View(y)
            child.parent = self
            return child
        }
    }
    
    func export(within: CGRect) -> UIView {
        let frame = transform.export(within: within)
        
        let view = UIView(frame: frame)
        view.backgroundColor = background
        
        for child in children {
            let c = child.export(within: within - layout.padding)
            view.addSubview(c)
        }
        
        return view
    }
    
}
