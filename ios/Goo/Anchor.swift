import Foundation

class Anchor : Bounds {
    
    static let topLeft = Anchor()
    static let topCenter = Anchor(minX: 0.5, maxX: 0.5)
    static let topRight = Anchor(minX: 1, maxX: 1)
    static let centerLeft = Anchor(minY: 0.5, maxY: 0.5)
    static let center = Anchor(minX: 0.5, minY: 0.5, maxX: 0.5, maxY: 0.5)
    static let centerRight = Anchor(minX: 1, minY: 0.5, maxX: 1, maxY: 0.5)
    static let bottomLeft = Anchor(minY: 1, maxY: 1)
    static let bottomCenter = Anchor(minX: 0.5, minY: 1, maxX: 0.5, maxY: 1)
    static let bottomRight = Anchor(minX: 1, minY: 1, maxX: 1, maxY: 1)
    static let topFill = Anchor(maxX: 1)
    static let horizontalFill = Anchor(minY: 0.5, maxX: 1, maxY: 0.5)
    static let bottomFill = Anchor(minY: 1, maxX: 1, maxY: 1)
    static let leftFill = Anchor(maxY: 1)
    static let verticalFill = Anchor(minX: 0.5, maxX: 0.5, maxY: 1)
    static let rightFill = Anchor(minX: 1, maxX: 1, maxY: 1)
    static let fill = Anchor(maxX: 1, maxY: 1)
    
    static let dictionary: [String: Anchor] = [
        "top-left": .topLeft,
        "top-center": .topCenter,
        "top-right": .topRight,
        "center-left": .centerLeft,
        "center": .center,
        "center-right": .centerRight,
        "bottom-left": .bottomLeft,
        "bottom-center": .bottomCenter,
        "bottom-right": .bottomRight,
        "top-fill": .topFill,
        "horizontal-fill": .horizontalFill,
        "bottom-fill": .bottomFill,
        "left-fill": .leftFill,
        "vertical-fill": .verticalFill,
        "right-fill": .rightFill,
        "fill": .fill
    ]
    
}
