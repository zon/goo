import XCTest
import Yaml
import Pods_Goo

class VerticalLayoutTests: BaseTestCase {
    
    func testDefault() {
        let screen = UIScreen.main.bounds
        let yaml = load(resource: "vertical")
        let e = Element.root(yaml)
        let r = e.export()
        
        equal(r.frame.width, screen.width)
        equal(r.frame.height, screen.height)
        equal(r.backgroundColor, UIColor("#fff"))
        equal(r.subviews.count, 4)
        
        let a = r.subviews[0]
        equal(a.frame.minX, 60)
        equal(a.frame.minY, 60)
        equal(a.frame.width, r.frame.width - 120)
        equal(a.frame.height, 100)
        equal(a.backgroundColor, UIColor("#f00"))
        
        let b = r.subviews[1]
        equal(b.frame.minY, a.frame.maxY + 30)
        
        let c = r.subviews[2]
        equal(c.frame.minX, (r.frame.width - c.frame.width) / 2)
        equal(c.frame.minY, (r.frame.height - c.frame.height) / 2)
        equal(c.frame.width, 100)
        equal(c.frame.height, 100)
        
        let d = r.subviews[3]
        equal(d.frame.minX, 30)
        equal(d.frame.minY, r.frame.height - 30 - d.frame.height)
        equal(d.frame.width, r.frame.width - 60)
        equal(d.frame.height, 100)
        
    }
    
}
