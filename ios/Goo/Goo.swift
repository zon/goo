import Foundation
import Yaml

class Goo {
    
    static func load(bundle: Bundle, resource: String) throws -> String {
        if let url = bundle.url(forResource: resource, withExtension: "yaml") {
            return try String(contentsOf: url)
        } else {
            throw GooError.notFound(path: resource)
        }
    }
    
}
