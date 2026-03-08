import Button from "../common/Button";
import TextArea from "../common/forms/TextArea";
import { useEffect, useState } from "react";
import { hhService } from "../services/hhService";
import { userService } from "../services/userService";
import Input from "../common/forms/Input";
import { useNavigate } from "react-router";

export const Profile = ({ handleLogout }) => {
  const [inputData, setInputData] = useState({ name: "", notes: "" });
  const [savedData, setSavedData] = useState({ name: "", notes: "" });
  //   const [hhNotes, setHhNotes] = useState(inputData.notes || "");

  const hhId = localStorage.getItem("householdId");
  const userId = localStorage.getItem("userId");

  // TODO: Handle password change

  const handleChange = (e) => {
    const { id, value } = e.target;
    setInputData((oldData) => ({ ...oldData, [id]: value }));
  };

  useEffect(() => {
    fetchUserHHData();
  }, []);

  const navigate = useNavigate();

  const fetchUserHHData = async () => {
    try {
      const hhResponse = await hhService.getHh(Number(hhId));
      const userResponse = await userService.getUser(Number(userId));
      const combine = {
        notes: hhResponse.data.notes,
        name: userResponse.data.name,
      };
      setInputData(combine);
      setSavedData(combine);
    } catch (er) {
      console.error("Error fetching User or Household:", er);
      // TODO: Add error handling UI
    }
  };

  const handleSave = async (e) => {
    e.preventDefault();
    try {
      const householdDto = {
        notes: inputData.notes,
      };
      const hhResponse = await hhService.updateHh(hhId, householdDto);
      const savedNotes = hhResponse.data.notes;
      setInputData((oldData) => ({ ...oldData, notes: savedNotes }));
      setSavedData((oldData) => ({ ...oldData, notes: savedNotes }));
      if (inputData.name !== savedData.name) {
        const updatedUserName = inputData.name;
        const userDto = {
          name: updatedUserName,
        };
        const userResponse = await userService.updateUser(userId, userDto);
        const savedUserName = userResponse.data.name;
        setInputData((oldData) => ({ ...oldData, name: savedUserName }));
        setSavedData((oldData) => ({ ...oldData, name: savedUserName }));
      }
    } catch (er) {
      console.error("Error saving updates:", er);
      setInputData(savedData);
    }
  };

  // Should reset HH data to last saved state (after canceling changes)
  const handleCancel = (e) => {
    e.preventDefault();
    setInputData(savedData);
  };

  // TODO: Add User Delete Account Functionality
  const handleDelete = async (id, e) => {
    e.preventDefault();
    if (id && window.confirm("Are you sure you want to delete your account?")) {
      try {
        await userService.deleteUser(id);
        handleLogout();
        navigate("/");
      } catch (er) {
        console.error("Error deleting User:", er);
      }
    }
  };

  return (
    <div className="profile-page">
      <h1>Profile Details</h1>
      <h2>User</h2>
      <p>Name: </p>
      <Input
        id="name"
        value={inputData.name || ""}
        handleChange={handleChange}
      />
      <p>Email: {localStorage.getItem("email")}</p>
      <h3>Change Password</h3>
      {/* current password
            new password
            confirm new password */}
      <Button
        id="update-password"
        label="Update Profile"
        handleClick={handleCancel}
        classes="button"
      />
      <h2>Household</h2>
      <TextArea
        id="notes"
        label="Household Details"
        value={inputData.notes || ""}
        handleChange={handleChange}
      />
      <Button
        id="update-household"
        label="Update Household"
        handleClick={handleSave}
        classes="button"
      />
      <Button
        id="cancel-household-changes"
        label="Cancel Changes"
        handleClick={handleCancel}
        classes="button"
      />
      <br />
      <br />
      <br />
      <Button
        id="delete-account"
        label="Delete Account"
        handleClick={handleDelete}
        classes="button delete-button"
      />
    </div>
  );
};
