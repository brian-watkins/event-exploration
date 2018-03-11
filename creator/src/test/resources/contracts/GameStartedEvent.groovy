import org.springframework.cloud.contract.spec.Contract
import java.util.regex.Pattern

Contract.make {
    description 'should create game started event'
    label 'game.started.event'
    input {
        triggeredBy('emitGameStarted()')
    }
    outputMessage {
        sentTo 'game-events'
        headers {
            messagingContentType(applicationJson())
        }
        body([
            id       : $(consumer("56fb5ce5-d669-45ea-8f6d-04a52a5d2e7e"), producer(uuid())),
            "type"   : "Started",
            "instant": $(consumer("2018-03-10T23:47:56.000000Z"), producer(regex(Pattern.compile(/([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\.\d{6})?(Z|[+-][01]\d:[0-5]\d)/))))
        ])
    }
}
