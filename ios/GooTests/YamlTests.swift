import XCTest
import Yaml

class YamlTests: BaseTestCase {
    
    var yaml: Yaml!
    
    override func setUp() {
        super.setUp()
        yaml = load(resource: "basic")
    }
    
    func testShortColor() {
        assertEqual(yaml["shortColor"].color?.hexString(), "#FF0000FF")
    }
    
    func testMediumColor() {
        assertEqual(yaml["mediumColor"].color?.hexString(), "#00FF00FF")
    }
    
    func testAlphaColor() {
        assertEqual(yaml["alphaColor"].color?.hexString(), "#0000FF99")
    }
    
    func testMissingVector() {
        assertEqual(Vector(yaml["no-vector"]), nil)
    }
    
}
