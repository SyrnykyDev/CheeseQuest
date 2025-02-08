"use client";
import React, { ButtonHTMLAttributes, useEffect } from "react";

// *** STYLES ***
import styles from "./Button.module.scss";

interface IButtonProps
  extends React.HTMLAttributes<ButtonHTMLAttributes<never>> {
  buttonType?: "sans";
  buttonColor?: "green" | "blue" | "orange" | "gray";
}

const Button = (props: IButtonProps) => {
  const { children, buttonType = "sans", buttonColor = "green" } = props;

  let style = "";
  switch (buttonType) {
    case "sans":
      style = `${styles.button_common_sans} `;
      switch (buttonColor) {
        case "blue":
          style += ` ${styles.button_blue}`;
          break;
        case "green":
          style += ` ${styles.button_green}`;
          break;
        case "orange":
          style += ` ${styles.button_orange}`;
          break;

        case "gray":
          style += ` ${styles.button_gray}`;
          break;
      }

      break;
  }

  return (
    <div>
      <button className={style}>{children}</button>
    </div>
  );
};

export default Button;
