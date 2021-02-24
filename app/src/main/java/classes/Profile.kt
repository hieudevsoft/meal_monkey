package classes

class Profile(_userId:String?=null,_email: String?=null,
              _password: String?=null,_name:String?=null,
              _address:String?=null,_phoneNumber:String?=null,
              _dateOfBirth:String?=null,_gender:Int?=null,_urlImage:String?=null) :User(_userId,_email, _password) {
    var name = _name
    var address = _address
    var phoneNumber = _phoneNumber
    var dateOfBirth = _dateOfBirth
    var gender = _gender
    var urlImage = _urlImage
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "userId" to userID,
            "email" to email,
            "password" to password,
            "name" to name,
            "phone" to phoneNumber,
            "address" to address,
            "dateOfBirth" to dateOfBirth,
            "gender" to gender,
            "urlImage" to urlImage

        )
    }

    fun fromObject(map:Map<String,Any?>):Profile{
            this.userID = map["userId"].toString()
            this.email = map["email"].toString()
            this.password = map["password"].toString()
            this.name = map["name"].toString()
            this.address = map["address"].toString()
            this.phoneNumber = map["phone"].toString()
            this.dateOfBirth = map["dateOfBirth"].toString()
            this.gender = Integer.parseInt(map["gender"].toString())
            this.urlImage = map["urlImage"].toString()
        return this
    }

    override fun toString(): String {
        return "Profile(id = $userID,name='$name', address='$address', phoneNumber='$phoneNumber', dateOfBirth='$dateOfBirth', gender=$gender)"
    }


}