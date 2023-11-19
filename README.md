### API
* `GET /note/{id}` - returns note by id
* `PUT /note` - generates id, stores note and returns created note
* `POST /note/{id}` - modifies the note by id
* `DELETE /note/{id}` - deletes note by id
* `GET /note` - returns list of notes, request parameters
  * `tag` - allows filtering by tag, optional
  * `page` - allows retrieving specific page, defaults to 0
  * `size` - allows specifying page size, defaults to 20
* `GET /note/{id}/stats` - returns word count in note by id

### Commands
`./gradlew buildImage` - build docker image

`./gradlew test` - execute tests

### About project
`java/com/teletronics/test/TestApplication.java` - main class

MongoDB is not included in resulting docker image