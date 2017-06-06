import Foundation
import Yaml

class Goo {
    
    static func load(bundle: Bundle, resource: String) throws -> Yaml {
        if let url = bundle.url(forResource: resource, withExtension: "yaml") {
            let text = try String(contentsOf: url)
            return try Yaml.load(text)
        } else {
            throw GooError.notFound(path: resource)
        }
    }
    
}
