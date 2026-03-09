export const validation = (newPassword, confirmPassword) => {
  const error = [];

  // check for match
  if (newPassword !== confirmPassword) {
    error.push("Passwords do not match");
  }

  // check for length
  if (newPassword.length < 8) {
    error.push("Password must be at least 8 characters");
  }

  // regex check for characters
  const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}$/;
  if (!regex.test(newPassword)) {
    error.push(
      "Password must include uppercase, lowercase, and a number.",
    );
  }

  return {
    isValid: error.length === 0, 
    errors: error
  };
};
