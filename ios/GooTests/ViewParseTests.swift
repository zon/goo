import XCTest
import Pods_Goo

class ViewParseTests: BaseTestCase {
    
    func testParse() {
        let view = View(load(resource: "view"))
            
        assert(view.id == "thing")
        assert(view.anchor == Anchor.fill)
        assert(view.origin == Vector.half)
        assert(view.layout.type == .grid)
        assert(view.left == 5)
        assert(view.right == 7)
        assert(view.top == 11)
        assert(view.bottom == 13)
        assert(view.width == 17)
        assert(view.height == 19)
    }
    
}
