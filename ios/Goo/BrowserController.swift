import UIKit

class BrowserController: UIViewController {
    let resource: String
    
    required init(resource: String) {
        self.resource = resource
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func loadView() {
        let yaml = try! Goo.load(resource: resource)
        let element = Element.root(yaml)
        view = element.export()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
}

