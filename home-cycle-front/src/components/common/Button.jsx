const Button = ({ id, type, label, handleClick, classes, disabled }) => {
  return (
    <button
      id={`${id}-button`}
      type={type}
      onClick={handleClick}
      className={classes}
      disabled={disabled}
    >
      {disabled ? "Loading..." : label}
    </button>
  );
};

export default Button;
