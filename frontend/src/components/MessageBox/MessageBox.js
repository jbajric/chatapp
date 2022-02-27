import React from "react";
import "antd/dist/antd.css";
import "./MessageBox.css";
import { List, Input } from "antd";
import SockJsClient from "react-stomp";
import axios from "axios";

const HOST_URL = "http://localhost:8080/chatApp";
const USER_LEFT_URL = "http://localhost:8080/userLeft";

const TOPIC = "/topic/globalChat";
const SEND_GLOBAL_MESSAGE = "/evoltTask/sendGlobalMessage";

const MessageType = {
  PARTICIPANT_JOINED: "PARTICIPANT_JOINED",
  PARTICIPANT_LEFT: "PARTICIPANT_LEFT",
  GLOBAL: "GLOBAL",
  PRIVATE: "PRIVATE",
};

const createMessage = (from, to, timestamp, content, messageType) => {
  return {
    from: from,
    to: to,
    timestamp: timestamp,
    content: content,
    messageType: messageType,
  };
};

const getCurrentTime = () => {
  return new Date().toLocaleString("en-GB");
};

class MessageBox extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      messages: [],
    };
  }

  onMessageReceive = (msg) => {
    this.setState((prevState) => ({
      messages: [...prevState.messages, msg],
    }));
  };

  sendMessage = (value) => {
    this.clientRef.sendMessage(
      HOST_URL + SEND_GLOBAL_MESSAGE,
      JSON.stringify(
        createMessage(
          this.props.user,
          null,
          getCurrentTime(),
          value.target.value.trim(),
          MessageType["GLOBAL"]
        )
      )
    );
  };

  userLeft = () => {
    axios.get(USER_LEFT_URL, { params: { username: this.props.user } }).then();
  };

  render() {
    return (
      <div>
        <div id="listMessages">
          <List
            dataSource={this.state.messages}
            renderItem={(item) => (
              <List.Item id="listItem">
                {item["from"] +
                  " " +
                  new Date(item["timestamp"]).toLocaleString("en-GB") +
                  " " +
                  item["content"]}
              </List.Item>
            )}
          ></List>
        </div>
        <SockJsClient
          url={HOST_URL}
          topics={[TOPIC]}
          onMessage={this.onMessageReceive}
          ref={(client) => {
            this.clientRef = client;
          }}
          onDisconnect={() => {
            this.userLeft();
          }}
        />
        <div id="mssgInput">
          <Input
            placeholder="Enter message.."
            size="large"
            onPressEnter={this.sendMessage}
            allowClear="true"
          />
        </div>
      </div>
    );
  }
}
export default MessageBox;
