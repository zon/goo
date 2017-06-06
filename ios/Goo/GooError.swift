import Foundation

enum GooError : Error {
    case notFound(path: String)
    case invalidFile(path: String)
}
