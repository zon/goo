import Foundation
import Yaml

public enum LayoutType: String {
    case relative = "relative"
    case vertical = "vertical"
    case horizontal = "horizontal"
    case grid = "grid"
}

public class Layout {
    let type: LayoutType
    let padding: Inset
    let spacing: Vector
    let alignment: Alignment?
    let cellSize: Vector?
    let expand: Bool
    
    let weight: Double
    let ignore: Bool
    
    static let typeFallback = LayoutType.vertical
    static let paddingFallback = Inset.zero
    static let spacingFallback = Vector.zero
    static let weightFallback = 1.0
    
//    init(type: LayoutType = typeFallback, padding: Inset = paddingFallback, spacing: Vector = spacingFallback, ignore: Bool = false) {
//        self.type = type
//        self.padding = padding
//        self.spacing = spacing
//        self.ignore = ignore
//    }
    
    init(_ yaml: Yaml) {
        type = yaml["layout"].string.flatMap { LayoutType(rawValue: $0) } ?? Layout.typeFallback
        
        let paddingProp = yaml["padding"]
        padding = paddingProp.double.map { Inset($0) } ?? Inset(paddingProp) ?? Layout.paddingFallback
        
        let spacingProp = yaml["spacing"]
        spacing = Vector.one * spacingProp.double ?? Vector(spacingProp) ?? Layout.spacingFallback
        
        alignment = yaml["alignment"].string.flatMap { Alignment(rawValue: $0) }
        
        let cellSizeProp = yaml["cell-size"]
        cellSize = Vector.one * cellSizeProp.double ?? Vector(cellSizeProp)
        
        expand = yaml["expand"].bool ?? false
        
        weight = yaml["weight"].double ?? Layout.weightFallback
        
        ignore = yaml["ignore-layout"].bool ?? false
    }
    
}
