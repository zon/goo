import XCTest
import Yaml

class ElementParseTests: BaseTestCase {
    
    func testParse() {
        let yaml = load(resource: "view")
        let e = Element(yaml)
            
        equal(e.id, "thing")
        equal(e.transform.anchor, Anchor.fill)
        equal(e.transform.origin, Vector.half)
        equal(e.layout.type, .grid)
        equal(e.transform.left, 5)
        equal(e.transform.right, 7)
        equal(e.transform.top, 11)
        equal(e.transform.bottom, 13)
        equal(e.transform.width, 17)
        equal(e.transform.height, 19)
    }
    
    func testDefault() {
        let e = Element(Yaml.null)
        
        equal(e.id, nil)
        equal(e.transform.anchor, Anchor.topFill)
        equal(e.transform.origin, Vector.zero)
        equal(e.layout.type, LayoutType.vertical)
    }
    
}
