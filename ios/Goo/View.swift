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
        
        let padded = within - layout.padding
        switch layout.type {
        case .vertical:
            var y = 0.0
            for child in children {
                if child.layout.ignore {
                    view.addSubview(child.export(within: within))
                } else {
                    let v = child.export(within: padded - Inset(top: y))
                    view.addSubview(v)
                    y += Double(v.frame.maxY) - layout.padding.top + layout.spacing.y
                }
            }
            
        case .horizontal:
            var x = 0.0
            for child in children {
                if child.layout.ignore {
                    view.addSubview(child.export(within: within))
                } else {
                    let v = child.export(within: padded - Inset(left: x))
                    view.addSubview(v)
                    x += Double(v.frame.maxX) - layout.padding.left + layout.spacing.x
                }
            }
            
        default:
            for child in children {
                if child.layout.ignore {
                    view.addSubview(child.export(within: within))
                } else {
                    view.addSubview(child.export(within: padded))
                }
            }
        }
        
        return view
    }
    
}
