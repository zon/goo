import Foundation
import UIKit
import Yaml

public class Element {
    let id: String?
    let transform: Transform
    let layout: Layout
    
    let background: UIColor?
    
    weak var parent: Element?
    var children: [Element]
    
    private var _view: UIView? = nil
    
    var view: UIView? {
        get { return _view }
    }
    
    static func parse(bundle: Bundle, resource: String) throws -> Element {
        let yaml = try Goo.load(bundle: bundle, resource: resource)
        return Element(yaml)
    }
    
    static func root(_ yaml: Yaml) -> Element {
        let screen = UIScreen.main.bounds
        let transform = Transform(anchor: .fill, width: Double(screen.width), height: Double(screen.height))
        return Element(yaml, transform: transform)
    }
    
    init(_ yaml: Yaml, transform: Transform? = nil) {
        id = yaml["id"].string
        self.transform = transform ?? Transform(yaml)
        layout = Layout(yaml)
        
        background = yaml["background"].color
        
        children = []
        children = yaml["children"].arrayValue.map { y in
            let child = Element(y)
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
        
        _view = view
        return view
    }
    
    func export() -> UIView {
        return export(within: CGRect(x: 0, y: 0, width: transform.width, height: transform.height))
    }
    
}
