import XCTest
import Pods_Goo

class ViewParseTests: BaseTestCase {
    
    func testParse() {
        let view = View(load(resource: "view"))
            
        assert(view.id == "thing")
        assert(view.transform.anchor == Anchor.fill)
        assert(view.transform.origin == Vector.half)
        assert(view.layout.type == .grid)
        assert(view.transform.left == 5)
        assert(view.transform.right == 7)
        assert(view.transform.top == 11)
        assert(view.transform.bottom == 13)
        assert(view.transform.width == 17)
        assert(view.transform.height == 19)
    }
    
}
