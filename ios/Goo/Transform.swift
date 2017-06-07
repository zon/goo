import Foundation
import Yaml
import UIKit

class Transform {
    let anchor: Anchor
    let origin: Vector
    let left: Double
    let right: Double
    let top: Double
    let bottom: Double
    let width: Double
    let height: Double
    
    static let anchorDefault = Anchor.topFill
    static let originDefault = Vector.zero
    
    init(
        anchor: Anchor = anchorDefault,
        origin: Vector = originDefault,
        left: Double = 0,
        right: Double = 0,
        top: Double = 0,
        bottom: Double = 0,
        width: Double = 0,
        height: Double = 0
    ) {
        self.anchor = anchor
        self.origin = origin
        self.left = left
        self.right = right
        self.top = top
        self.bottom = bottom
        self.width = width
        self.height = height
    }
    
    convenience init(size: CGSize) {
        self.init(width: Double(size.width), height: Double(size.height))
    }
    
    convenience init(_ yaml: Yaml) {
        let anchorProp = yaml["anchor"]
        let anchor = anchorProp.string.flatMap { Anchor.dictionary[$0] } ?? Anchor(anchorProp) ?? Transform.anchorDefault
        
        let originProp = yaml["origin"]
        let origin = originProp.string == "center" ? Vector.half : Vector(originProp) ?? Transform.originDefault
        
        self.init(
            anchor: anchor,
            origin: origin,
            left: yaml["left"].double ?? yaml["x"].double ?? 0,
            right: yaml["right"].double ?? 0,
            top: yaml["top"].double ?? yaml["y"].double ?? 0,
            bottom: yaml["bottom"].double ?? 0,
            width: yaml["width"].double ?? 0,
            height: yaml["height"].double ?? 0
        )
    }
    
    func export(parent: CGRect? = nil) -> CGRect {
        if let parent = parent {
            var x = 0.0
            var y = 0.0
            var w = 0.0
            var h = 0.0
            let bounds = anchor * Vector(parent.size)
            
            if anchor.xCollapsed {
                x = bounds.min.x + left - width * origin.x
                w = width
                
            } else {
                x = bounds.min.x + left
                w = bounds.max.x - bounds.min.x - left - right
            }
            
            if anchor.yCollapsed {
                y = bounds.min.y + top - height * origin.y
                h = height
                
            } else {
                y = bounds.min.y + top
                h = bounds.max.y - bounds.min.y - top - bottom
            }
            
            return CGRect(x: x, y: y, width: w, height: h)
            
        } else {
            return CGRect(x: 0, y: 0, width: width, height: height)
        }
    }
    
    func export(parent: UIView? = nil) -> CGRect {
        return export(parent: parent.map { $0.frame })
    }
    
}
