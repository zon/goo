import Foundation
import Yaml

struct Vector {
    let x: Double
    let y: Double
    
    static let zero = Vector(0, 0)
    static let half = Vector(0.5, 0.5)
    static let one = Vector(1, 1)
    
    init(_ x: Double, _ y: Double) {
        self.x = x
        self.y = y
    }
    
    init?(_ yaml: Yaml) {
        if let x = yaml.arrayValue[safe: 0]?.double, let y = yaml.arrayValue[safe: 1]?.double {
            self.x = x
            self.y = y
        } else {
            let x = yaml["x"].double
            let y = yaml["y"].double
            if x != nil || y != nil {
                self.x = x ?? 0
                self.y = y ?? 0
            } else {
                return nil
            }
        }
    }
    
}

func ==(left: Vector, right: Vector) -> Bool {
    return left.x == right.x && left.y == right.y
}

func *(left: Vector, right: Double) -> Vector {
    return Vector(left.x * right, left.y * right)
}

func *(left: Double, right: Vector) -> Vector {
    return Vector(left * right.x, left * right.y)
}

func *(left: Vector?, right: Double?) -> Vector? {
    if let left = left, let right = right {
        return left * right
    } else {
        return nil
    }
}

func *(left: Double?, right: Vector?) -> Vector? {
    if let left = left, let right = right {
        return left * right
    } else {
        return nil
    }
}
