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
    let alignment: Alignment
    let cellSize: Vector?
    let distribute: Vector
    let fit: Vector
    
    let weight: Double
    let ignore: Bool
    
    init(_ yaml: Yaml) {
        type = yaml["layout"].string.flatMap { LayoutType(rawValue: $0) } ?? .vertical
        
        let paddingProp = yaml["padding"]
        padding = paddingProp.double.map { Inset($0) } ?? Inset(paddingProp) ?? Inset.zero
        
        let spacingProp = yaml["spacing"]
        spacing = Vector.one * spacingProp.double ?? Vector(spacingProp) ?? Vector.zero
        
        alignment = yaml["alignment"].string.flatMap { Alignment(rawValue: $0) } ?? .topLeft
        
        let cellSizeProp = yaml["cell-size"]
        cellSize = Vector.one * cellSizeProp.double ?? Vector(cellSizeProp)
        
        distribute = Vector.direction(yaml["distribute"])
        
        fit = Vector.direction(yaml["fit"])
        
        weight = yaml["weight"].double ?? 1
        
        ignore = yaml["ignore-layout"].bool ?? false
    }
    
}
