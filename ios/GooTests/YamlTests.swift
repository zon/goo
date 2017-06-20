import XCTest
import Yaml
import Pods_Goo

class YamlTests: BaseTestCase {
    
    var yaml: Yaml!
    
    override func setUp() {
        super.setUp()
        yaml = load(resource: "basic")
    }
    
    func testArrayVector() {
        equal(Vector(yaml["arrayVector"]), Vector(34, 56))
    }
    
    func testDictVector() {
        equal(Vector(yaml["dictVector"]), Vector(45, 67))
    }
    
    func testDirection() {
        equal(Vector.direction(yaml["direction"]), Vector.left)
    }
    
    func testMissingVector() {
        equal(Vector(yaml["no-vector"]), nil)
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
    
}
