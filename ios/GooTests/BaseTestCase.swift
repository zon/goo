import XCTest
import Yaml

class BaseTestCase: XCTestCase {
    
    func load(resource: String) -> Yaml {
        let bundle = Bundle(for: type(of: self))
        do {
            return try Goo.load(bundle: bundle, resource: resource)
        } catch let e {
            assertionFailure(e.localizedDescription)
            return Yaml.null
        }
    }
    
}
