import Foundation
import UIKit
import Yaml

public class Element {
    let id: String?
    let transform: Transform
    let layout: Layout
    
    let background: UIColor?
    
    var children: [Element]
    
    var view: UIView!
    
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
        
        children = yaml["children"].arrayValue.map { Element($0) }
        
        populate()
    }
    
    func populate() {
        let view = UIView()
        
        for child in children {
            view.addSubview(child.view)
        }
        
        self.view = view
    }
    
    func update(within: CGRect) {
        
        view.frame = transform.export(within: within)
        
        let padded = CGRect(origin: CGPoint.zero, size: view.frame.size) - layout.padding
        if layout.type != .relative {
            let included = children.filter { !$0.layout.ignore }
            
            var size = Vector.zero
            var spacing = Vector.zero
            var delta = Vector.zero
            var weight = 0.0
            if layout.distribute != .zero {
                size = Vector(
                    included.map { $0.transform.width }.reduce(0, +),
                    included.map { $0.transform.height }.reduce(0, +)
                )
                spacing = layout.spacing * Double(max(included.count - 1, 0))
                delta = Vector(padded.size) - spacing - size
                weight = included.map { $0.layout.weight }.reduce(0, +)
            }
            
            var position = CGPoint.zero
            for child in included {
                var cell = CGRect(
                    origin: padded.origin + position,
                    size: padded.size
                )
                
                if layout.distribute.x != 0 && layout.type == .horizontal {
                    cell.size.width = CGFloat(child.transform.width + delta.x * (child.layout.weight / weight))
                
                } else if layout.distribute.y != 0 && layout.type == .vertical {
                    cell.size.height = CGFloat(child.transform.height + delta.y * (child.layout.weight / weight))
                }
                
                child.update(within: cell)
                
                if layout.type == .horizontal {
                    position.x = cell.maxX + CGFloat(layout.spacing.x - layout.padding.left)
                    
                } else if layout.type == .vertical {
                    position.y = cell.maxY + CGFloat(layout.spacing.y - layout.padding.right)
                }
            }
            
            for child in children.filter({ $0.layout.ignore }) {
                child.update(within: padded)
            }
            
        } else {
            for child in children {
                child.update(within: padded)
            }
        }
        
        view.backgroundColor = background
    }
    
    func update() {
        return update(within: CGRect(x: 0, y: 0, width: transform.width, height: transform.height))
    }
    
}
