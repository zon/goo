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
    
    static let typeDefault = LayoutType.vertical
    static let paddingDefault = Inset.zero
    static let spacingDefault = Vector.zero
    
    init(type: LayoutType = typeDefault, padding: Inset = paddingDefault, spacing: Vector = spacingDefault) {
        self.type = type
        self.padding = padding
        self.spacing = spacing
    }
    
    init(_ yaml: Yaml) {
        type = yaml["type"].string.flatMap { LayoutType(rawValue: $0) } ?? Layout.typeDefault
        
        padding = Inset(yaml["padding"]) ?? Layout.paddingDefault
        
        let spacing = yaml["spacing"]
        self.spacing = Vector(spacing) ?? Vector.one * spacing.double ?? Layout.spacingDefault
    }
    
}
