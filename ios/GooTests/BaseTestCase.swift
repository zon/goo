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
    
    func equal<T: Equatable>(_ a: T?, _ b: T?) {
        let ad = a.map { String(describing: $0) } ?? "nil"
        let bd = b.map { String(describing: $0) } ?? "nil"
        assert(a == b, "\(ad) is not equal to \(bd)")
    }
    
}
