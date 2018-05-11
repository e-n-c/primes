import groovyx.net.http.*
import spock.lang.Specification


class PrimesApiSpecTest extends Specification {
  def "primes api is up and working"() {
    given: 
      def client = new RESTClient("http://api:8080/")
    when:
      def response = client.get(
        path: 'primes/lessthan/7',
        requestContentType: ContentType.JSON,
        headers: ['Content-Type' : 'application/json']
      )
    then:
      assert response.status == 200
  }
}
