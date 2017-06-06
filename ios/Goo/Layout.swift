import Foundation
import Yaml

enum LayoutType {
    case absolute
    case vertical
    case horizontal
    case grid
}

class Layout {
    let type: LayoutType
    let padding: Inset
    let spacing: Vector
    
    static let typeDefault = LayoutType.vertical
    static let paddingDefault = Inset.zero
    static let spacingDefault = Vector.zero
    static let typeDictionary: [String: LayoutType] = [
        "absolute": .absolute,
        "vertical": .vertical,
        "horizontal": .horizontal,
        "grid": .grid
    ]
    
    init(type: LayoutType = typeDefault, padding: Inset = paddingDefault, spacing: Vector = spacingDefault) {
        self.type = type
        self.padding = padding
        self.spacing = spacing
    }
    
    init(_ yaml: Yaml) {
        if let key = yaml["type"].string {
            type = Layout.typeDictionary[key] ?? Layout.typeDefault
        } else {
            type = Layout.typeDefault
        }
        
        padding = Inset(yaml["padding"]) ?? Layout.paddingDefault
        
        let spacing = yaml["spacing"]
        self.spacing = Vector(spacing) ?? Vector.one * spacing.double ?? Layout.spacingDefault
    }
    
}
