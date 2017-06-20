import Foundation
import Yaml

public struct Vector: Hashable, Equatable, CustomStringConvertible {
    let x: Double
    let y: Double
    
    static let zero = Vector(0, 0)
    static let half = Vector(0.5, 0.5)
    static let one = Vector(1, 1)
    static let left = Vector(1, 0)
    static let up = Vector(0, 1)
    
    public var hashValue: Int {
        return x.hashValue ^ y.hashValue &* 9719
    }
    
    public var description: String {
        return "(\(x), \(y))"
    }
    
    init(_ x: Double, _ y: Double) {
        self.x = x
        self.y = y
    }
    
    init(_ size: CGSize) {
        x = Double(size.width)
        y = Double(size.height)
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
    
    static func direction(_ yaml: Yaml) -> Vector {
        switch yaml.string {
        case "vertical"?:
            return .up
        case "horizontal"?:
            return .left
        case "both"?:
            return .one
        default:
            return .zero
        }
    }
    
}

public func ==(left: Vector, right: Vector) -> Bool {
    return left.x == right.x && left.y == right.y
}

public func +(left: Vector, right: Vector) -> Vector {
    return Vector(left.x + right.x, left.y + right.y)
}

public func -(left: Vector, right: Vector) -> Vector {
    return Vector(left.x - right.x, left.y - right.y)
}

public func *(left: Vector, right: Vector) -> Vector {
    return Vector(left.x * right.x, left.y * right.y)
}

public func *(left: Vector, right: Double) -> Vector {
    return Vector(left.x * right, left.y * right)
}

public func *(left: Double, right: Vector) -> Vector {
    return Vector(left * right.x, left * right.y)
}

public func *(left: Vector?, right: Double?) -> Vector? {
    if let left = left, let right = right {
        return left * right
    } else {
        return nil
    }
}

public func *(left: Double?, right: Vector?) -> Vector? {
    if let left = left, let right = right {
        return left * right
    } else {
        return nil
    }
}
