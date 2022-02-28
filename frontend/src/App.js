import React from "react";
import "./css/App.css";
import "antd/dist/antd.css";

import { Button, Layout } from "antd";
import { DownloadOutlined } from "@ant-design/icons";
import { Alert } from "@mui/material";
import ActiveUsersSidebar from "./components/ActiveUsersSidebar/ActiveUsersSidebar";
import MessageBox from "./components/MessageBox/MessageBox";
import axios from "axios";

const { Header, Footer, Sider, Content } = Layout;

const HOST_URL = "http://localhost:8080";
const USER_JOINED_URL = "/userJoined";
const USER_LEFT_URL = "/userLeft";
const ACTIVE_USERS_URL = "/activeUsers";
const ALL_SENT_MESSAGES = "/allSentMessages";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      activeUsers: [],
      allSentMessages: [],
      username: "",
      loadedMessages: false,
    };
  }

  handleClose = () => {
    this.setState(() => ({
      loadedMessages: false,
    }));
  };

  handleWindowClosed = () => {
    window.addEventListener("beforeunload", (event) => {
      this.userLeftAction();
    });
  };

  userJoinedAction = () => {
    axios.get(HOST_URL + USER_JOINED_URL).then((response) => {
      this.setState(() => ({
        username: response.data,
      }));
    });
  };

  userLeftAction = () => {
    axios
      .get(HOST_URL + USER_LEFT_URL, {
        params: { username: this.state.username },
      })
      .then();
  };

  fetchActiveUsers = () => {
    axios.get(HOST_URL + ACTIVE_USERS_URL).then((response) => {
      this.setState(() => ({
        activeUsers: response.data,
      }));
    });
  };

  fetchAllSentMessages = () => {
    axios.get(HOST_URL + ALL_SENT_MESSAGES).then((response) => {
      this.setState(() => ({
        allSentMessages: response.data,
        loadedMessages: true,
      }));
    });
  };

  componentWillUnmount() {
    this.userLeftAction();
  }

  componentDidMount() {
    this.fetchActiveUsers();
    this.userJoinedAction();
    this.handleWindowClosed();
  }

  componentDidUpdate() {
    this.fetchActiveUsers();
  }

  render() {
    return (
      <div className="App">
        <Layout>
          <Header id="header">Chat Application</Header>
          <Layout id="layout">
            <Sider id="sider">
              <Button
                id="loadOldMessagesButton"
                type="primary"
                icon={<DownloadOutlined />}
                size="medium"
                onClick={this.fetchAllSentMessages}
              >
                Load old messages
              </Button>
              <ActiveUsersSidebar activeUsers={this.state.activeUsers} />
            </Sider>
            <Content id="content">
              {this.state.loadedMessages ? (
                <Alert
                  onClose={() => {
                    this.handleClose();
                  }}
                  variant="filled"
                >
                  You have successfully loaded all sent messages from this chat
                  room!
                </Alert>
              ) : null}

              {this.state.username ? (
                <Alert
                  onClose={() => {
                    this.handleClose();
                  }}
                  severity="info"
                  variant="filled"
                >
                  Hello there! Your username is: {this.state.username}.
                </Alert>
              ) : null}

              <MessageBox
                user={this.state.username}
                allSentMessages={this.state.allSentMessages}
              />
            </Content>
          </Layout>
          <Footer id="footer">
            Developed by Jasmin Bajrić in february 2022.
          </Footer>
        </Layout>
      </div>
    );
  }
}

export default App;
