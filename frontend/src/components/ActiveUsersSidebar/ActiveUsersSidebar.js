import React from "react";
import "antd/dist/antd.css";
import "./ActiveUsersSidebar.css";
import { List } from "antd";

class ActiveUsersSidebar extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div id="activeUsersList">
        <List
          size="small"
          dataSource={this.props.activeUsers}
          renderItem={(item) => (
            <List.Item id="activeUserItem">{item}</List.Item>
          )}
        ></List>
      </div>
    );
  }
}

export default ActiveUsersSidebar;
