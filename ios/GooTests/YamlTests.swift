import XCTest
import Yaml

class YamlTests: BaseTestCase {
    
    var yaml: Yaml!
    
    override func setUp() {
        super.setUp()
        yaml = load(resource: "basic")
    }
    
    func testColor() {
        assert(yaml["color"].color == UIColor("FF000099"))
    }
    
}
