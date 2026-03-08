import { Link } from "react-router";
import Button from "../common/Button";

const Nav = ({ logInStatus, handleLogout }) => {

  return (
    <div className="nav-menu">
      <Link className="link new-task" to="/newtask" >
        New Task
      </Link>
      <Link className="link" to="/" >
        Home
      </Link>
      <Link className="link" to="/about">
        About
      </Link>
      {logInStatus && (
        <Link className="link" to="/profile">
          Profile
        </Link>
      )}
      {logInStatus && (
        <Button
          id="logout"
          label="Log Out"
          handleClick={handleLogout}
          classes="button"
        />
      )}
    </div>
  );
};

export default Nav;
