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
    
    static let fallback = topFill
    static let originFallback = Vector.zero
    
    static let strings: [String: Anchor] = [
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
    
    static let defaultOrigins: [Anchor: Vector] = [
        topLeft: Vector(0, 0),
        topCenter: Vector(0.5, 0),
        topRight: Vector(1, 0),
        centerLeft: Vector(0, 0.5),
        center: Vector(0.5, 0.5),
        centerRight: Vector(1, 0.5),
        bottomLeft: Vector(0, 1),
        bottomCenter: Vector(0.5, 1),
        bottomRight: Vector(1, 1),
        topFill: Vector(0, 0),
        horizontalFill: Vector(0, 0.5),
        bottomFill: Vector(0, 1),
        leftFill: Vector(0, 0),
        verticalFill: Vector(0.5, 0),
        rightFill: Vector(1, 0),
        fill: Vector(0, 0)
    ]
    
    var xCollapsed: Bool {
        return min.x == max.x
    }
    
    var yCollapsed: Bool {
        return min.y == max.y
    }
    
}
