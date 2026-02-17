const TextArea = ({
  id,
  label,
  rows,
  cols,
  value,
  ref,
  required,
  handleChange,
}) => {
  return (
    <>
      <label htmlFor={id}>
        {label}
        {required && "*"}
      </label>
      <textarea
        id={id}
        rows={rows || "4"}
        cols={cols || "35"}
        value={value}
        ref={ref}
        onChange={handleChange}
      ></textarea>
    </>
  );
};

export default TextArea;
