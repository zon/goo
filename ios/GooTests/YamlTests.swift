import XCTest
import Yaml

class YamlTests: BaseTestCase {
    
    var yaml: Yaml!
    
    override func setUp() {
        super.setUp()
        yaml = load(resource: "basic")
    }
    
    func testShortColor() {
        equal(yaml["shortColor"].color?.hexString(), "#FF0000FF")
    }
    
    func testMediumColor() {
        equal(yaml["mediumColor"].color?.hexString(), "#00FF00FF")
    }
    
    func testAlphaColor() {
        equal(yaml["alphaColor"].color?.hexString(), "#0000FF99")
    }
    
    func testMissingVector() {
        equal(Vector(yaml["no-vector"]), nil)
    }
    
}
