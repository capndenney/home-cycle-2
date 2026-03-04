import Button from "../common/Button";
import Input from "../common/forms/Input";
import { useState } from "react";
import { userService } from "../services/userService";

const LogIn = ({ setLogInStatus, clicked, setClicked }) => {
  const credentialFormat = { email: "", password: "" };
  const [creds, setCreds] = useState(credentialFormat);

  const handleChange = (e) => {
    const { id, value } = e.target;
    setCreds((oldData) => ({ ...oldData, [id]: value }));
  };

  const handleClick = async (e) => {
    e.preventDefault();
    try {
      const response = await userService.login(creds);
      localStorage.setItem("user_token", response.token);
      localStorage.setItem("email", response.email);
      localStorage.setItem("userId", response.userId);
      localStorage.setItem("householdId", response.householdId);
      setLogInStatus(true);
    } catch (er) {
      console.error("Login failed:", er);
      setClicked((prev) => prev + 1);
    }
  }

  return (
    <>
      <div className="log-in card add-blur">
        <h2>Log In</h2>
        {clicked > 0 && <h4 className="invalid">Invalid Credentials!</h4>}

        <Input
          label="Email:"
          value={creds.email}
          id="email"
          handleChange={handleChange}
        />
        <Input
          label="Password:"
          value={creds.password}
          type="password"
          id="password"
          handleChange={handleChange}
        />
        <Button label="Log In" id="log-in-button" handleClick={handleClick} />
      </div>
      <p className="about intro card" id="intro">Home Cycle is the solution to recurring maintenance around the home. As a homeowner, I have lots of small things I have to do every day, week, month and year it seems.  I built this to help me stay organized, and hopefully it does the same for others.</p>
    </>
  );
};

export default LogIn;
