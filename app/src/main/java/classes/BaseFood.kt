package classes
open class BaseFood(
    open var urlImage:Int, open var nameFood:String,
    open var typeFood:String, open var averageRating:Double,
    open var numberOfRatings:Int, open var nameRestaurants:String) {
}

data class OrderFood(
    override var urlImage:Int, override var nameFood:String,
    override var typeFood:String, override var averageRating:Double,
    override var numberOfRatings:Int, override var nameRestaurants:String,
    var restaurantAddress:String,var priceFood:Double, var count:Int
    ):
    BaseFood(urlImage,nameFood,
    typeFood,averageRating,numberOfRatings,nameRestaurants)