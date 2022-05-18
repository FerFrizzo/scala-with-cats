package Chapter2

import java.util.NoSuchElementException

object ConnectorExecutor extends App {
  val emailClient = new EmailClient
  val slackClient = new SlackClient
  // val otherClient = new OtherClient
  // ...

  /**
   * Don't change the signature of this function
   */
  def executeConnector(connector: String, operation: String, params: Map[String, String]): String = connector.toLowerCase match {
    case c if c == "slack" || c == "email" => sendMessage(operation, params)
    case _ => "ERROR"
  }

  private def sendMessage(operation: String, params: Map[String, String]) = operation.toLowerCase match {
    case o if o == "sendemail" => sendEmail(params)
    case o if o == "sendchannelmessage" => sendChannelMessage(params)
    case o if o == "sendprivatemessage" => sendPrivateMessage(params)
    case _ => "ERROR"
  }

  private def sendPrivateMessage(params: Map[String, String]) = {
    val (recipient: String, message: String, conf: String) = getPrivateMessageParams(params)
    slackClient.sendPrivateMessage(recipient, message, conf)
  }

  private def sendChannelMessage(params: Map[String, String]) = {
    val (channel: String, message: String, conf: String) = getSendChannelParams(params)
    slackClient.sendChannelMessage(channel, message, conf)
  }

  private def sendEmail(params: Map[String, String]): String = {
    val (to: String, cc: String, bcc: String, body: String, conf: String) = getEmailParams(params)
    emailClient.sendEmail(to, cc, bcc, body, conf)
  }

  private def getPrivateMessageParams(params: Map[String, String]): (String, String, String) =
    try {
      val recipient = params("recipient")
      val message = params("message")
      val conf = ConfigurationRepository.findConnectorConfiguration("Slack")
      (recipient, message, conf)
    } catch {
      case e: NoSuchElementException => throw new Exception(s"Error reading params, please make sure to inform it. ${e.getMessage}")
      case e: Exception => throw new Exception(s"Unexpected error with message: ${e.getMessage}")
    }

  private def getSendChannelParams(params: Map[String, String]): (String, String, String) =
    try {
      val channel = params("channel")
      val message = params("message")
      val conf = ConfigurationRepository.findConnectorConfiguration("Slack")
      (channel, message, conf)
    } catch {
      case e: NoSuchElementException => throw new Exception(s"Error reading params, please make sure to inform it. ${e.getMessage}")
      case e: Exception => throw new Exception(s"Unexpected error with message: ${e.getMessage}")
    }

  private def getEmailParams(params: Map[String, String]): (String, String, String, String, String) =
    try {
      val to = params("to")
      val cc = params("cc")
      val bcc = params("bcc")
      val body = params("body")
      val conf = ConfigurationRepository.findConnectorConfiguration("Email")
      (to, cc, bcc, body, conf)
    } catch {
      case e: NoSuchElementException => throw new Exception(s"Error reading params, please make sure to inform it. ${e.getMessage}")
      case e: Exception => throw new Exception(s"Unexpected error with message: ${e.getMessage}")
    }
}



/*
     _____       _
    (_____)     (_)_
       _   ____  _| |_
      | | |  _ \| |  _)
     _| |_| | | | | |__
    (_____)_| |_|_|\___)

 */


object Solution11 {

  /**
   * Do not edit this function
   * It's required by the platform
   */
  def solution(connector: String, operation: String, params: Array[String]): String = {
    ConnectorExecutor.executeConnector(connector, operation, parseParams(params))
  }

  /**
   * Do not edit this function
   * You can assume this parsing is capable of handling correctly all the input
   */
  def parseParams(params: Array[String]): Map[String, String] = params.toList.map { param =>
    param.split(":").toList match {
      case key :: value :: Nil => key -> value
      case _ => throw new Exception("Don't worry, there are not test cases passing through here")
    }
  }.toMap

}

/*
      ______             ___
     / _____)           / __)
    | /      ___  ____ | |__
    | |     / _ \|  _ \|  __)
    | \____| |_| | | | | |
     \______)___/|_| |_|_|

 */

/**
 * Do not edit this class
 * Ignore its implementation and treat it as a black box
 * (check its signatures and docs)
 */
object ConfigurationRepository {

  /**
   * @return the configuration for the specified connector or null if the configuration is missing
   */
  def findConnectorConfiguration(name: String): String = {
    if (name == "Slack") {
      "SLACK CONF"
    } else {
      null
    }
  }


}

/*

          ______ _ _
         / _____) (_)            _
        | /     | |_  ____ ____ | |_   ___
        | |     | | |/ _  )  _ \|  _) /___)
        | \_____| | ( (/ /| | | | |__|___ |
         \______)_|_|\____)_| |_|\___|___/


 */

/**
 * Do not edit this class
 * Ignore its implementation and treat it as a black box
 * (check its signatures and docs)
 */
class EmailClient {

  /**
   * @throws NullPointerException if any parameter is null
   */
  def sendEmail(to: String, cc: String, bcc: String, body: String, configuration: String) = "EMAIL OUTPUT"
}

/**
 * Do not edit this class
 * Ignore its implementation and treat it as a black box
 * (check its signatures and docs)
 */
class SlackClient {
  /**
   * @throws NullPointerException if any parameter is null
   */
  def sendChannelMessage(channel: String, message: String, configuration: String) = "SLACK CHANNEL MESSAGE OUTPUT"

  /**
   * @throws NullPointerException if any parameter is null
   */
  def sendPrivateMessage(recipient: String, message: String, configuration: String) = "SLACK PRIVATE MESSAGE OUTPUT"
}
