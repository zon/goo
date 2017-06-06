import XCTest
import Pods_Goo

class ViewParseTests: XCTestCase {
    
    func testParse() {
        do {
            let bundle = Bundle(for: type(of: self))
            let view = try View.parse(bundle: bundle, resource: "view")
            
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
            
        } catch let e {
            assertionFailure(e.localizedDescription)
        }
        
    }
    
}
