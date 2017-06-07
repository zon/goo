import Foundation
import Yaml

class Bounds {
    let min: Vector
    let max: Vector
    
    init(min: Vector, max: Vector) {
        self.min = min
        self.max = max
    }
    
    convenience init(minX: Double = 0, minY: Double = 0, maxX: Double = 0, maxY: Double = 0) {
        self.init(min: Vector(minX, minY), max: Vector(maxX, maxY))
    }
    
    convenience init?(_ yaml: Yaml) {
        if let min = Vector(yaml["min"]), let max = Vector(yaml["max"]) {
            self.init(min: min, max: max)
        } else {
            return nil
        }
    }
    
}

func ==(left: Bounds, right: Bounds) -> Bool {
    return left.min == right.min && left.max == right.max
}

func *(left: Bounds, right: Vector) -> Bounds {
    return Bounds(min: left.min * right, max: left.max * right)
}
