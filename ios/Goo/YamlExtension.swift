import Foundation
import Yaml

extension Yaml {
    
    var arrayValue: [Yaml] {
        get {
            return array ?? []
        }
    }
    
}
