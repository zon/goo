import Foundation
import Yaml

enum LayoutType: String {
    case absolute = "absolute"
    case vertical = "vertical"
    case horizontal = "horizontal"
    case grid = "grid"
}

class Layout {
    let type: LayoutType
    let padding: Inset
    let spacing: Vector
    
    static let typeFallback = LayoutType.vertical
    static let paddingFallback = Inset.zero
    static let spacingFallback = Vector.zero
    
    init(type: LayoutType = typeFallback, padding: Inset = paddingFallback, spacing: Vector = spacingFallback) {
        self.type = type
        self.padding = padding
        self.spacing = spacing
    }
    
    init(_ yaml: Yaml) {
        type = yaml["layout"].string.flatMap { LayoutType(rawValue: $0) } ?? Layout.typeFallback
        
        let paddingProp = yaml["padding"]
        padding = paddingProp.double.map { Inset($0) } ?? Inset(paddingProp) ?? Layout.paddingFallback
        
        let spacingProp = yaml["spacing"]
        spacing = Vector.one * spacingProp.double ?? Vector(spacingProp) ?? Layout.spacingFallback
    }
    
}
