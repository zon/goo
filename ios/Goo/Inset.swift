import Foundation
import Yaml

struct Inset {
    let left: Double
    let right: Double
    let top: Double
    let bottom: Double
    
    static let zero = Inset()
    
    init(left: Double = 0, right: Double = 0, top: Double = 0, bottom: Double = 0) {
        self.left = left
        self.right = right
        self.top = top
        self.bottom = bottom
    }
    
    init(_ value: Double) {
        left = value
        right = value
        top = value
        bottom = value
    }
    
    init?(_ yaml: Yaml) {
        let left = yaml["left"].double
        let right = yaml["right"].double
        let top = yaml["top"].double
        let bottom = yaml["bottom"].double
        if left != nil || right != nil || top != nil || bottom != nil {
            self.left = left ?? 0
            self.right = right ?? 0
            self.top = top ?? 0
            self.bottom = bottom ?? 0
        } else {
            return nil
        }
    }
    
}

func -(a: CGRect, b: Inset) -> CGRect {
    let left = CGFloat(b.left)
    let right = CGFloat(b.right)
    let top = CGFloat(b.top)
    let bottom = CGFloat(b.bottom)
    return CGRect(
        x: a.origin.x + left,
        y: a.origin.y + top,
        width: a.size.width - left - right,
        height: a.size.height - top - bottom
    )
}
