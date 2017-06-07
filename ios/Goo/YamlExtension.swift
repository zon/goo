import Foundation
import Yaml
import UIColor_Hex_Swift

extension Yaml {
    
    var cgFloat: CGFloat? {
        get {
            return double.map { v in CGFloat(v) }
        }
    }
    
    var arrayValue: [Yaml] {
        get {
            return array ?? []
        }
    }
    
    var color: UIColor? {
        get {
            if let hex = string {
                if hex.characters.first != "#" {
                    return UIColor("#" + hex)
                } else {
                    return UIColor(hex)
                }
            } else  {
                let red = self["red"].cgFloat ?? self["r"].cgFloat
                let green = self["green"].cgFloat ?? self["g"].cgFloat
                let blue = self["blue"].cgFloat ?? self["b"].cgFloat
                let alpha = self["alpha"].cgFloat ?? self["a"].cgFloat
                if red != nil && green != nil && blue != nil {
                    return UIColor(red: red ?? 0, green: green ?? 0, blue: blue ?? 0, alpha: alpha ?? 1)
                } else {
                    return nil
                }
            }
        }
    }
    
}
