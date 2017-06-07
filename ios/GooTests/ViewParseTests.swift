import XCTest
import Yaml

class ViewParseTests: BaseTestCase {
    
    func testParse() {
        let view = View(load(resource: "view"))
            
        assertEqual(view.id, "thing")
        assertEqual(view.transform.anchor, Anchor.fill)
        assertEqual(view.transform.origin, Vector.half)
        assertEqual(view.layout.type, .grid)
        assertEqual(view.transform.left, 5)
        assertEqual(view.transform.right, 7)
        assertEqual(view.transform.top, 11)
        assertEqual(view.transform.bottom, 13)
        assertEqual(view.transform.width, 17)
        assertEqual(view.transform.height, 19)
    }
    
    func testDefault() {
        let view = View(Yaml.null)
        
        assertEqual(view.id, nil)
        assertEqual(view.transform.anchor, Anchor.topFill)
        assertEqual(view.transform.origin, Vector.zero)
        assertEqual(view.layout.type, LayoutType.vertical)
    }
    
}
