import React from "react";
import Link from "next/link";

// *** STYLES ***
import styles from "./Footer.module.scss";

// *** INTERFACES ***
interface FooterProps {
  test?: boolean;
}
interface footerLink {
  href: string;
  text: string;
  disabled?: boolean;
}
interface footerColumn {
  title: string;
  linksArray: footerLink[];
}

const footerArray: footerColumn[] = [
  {
    title: "Links",
    linksArray: [
      { href: "/profile", text: "Profile", disabled: false },
      { href: "/createQuiz", text: "Create Quiz", disabled: false },
      { href: "/register", text: "Register", disabled: false },
      { href: "/login", text: "Log In", disabled: false },
    ],
  },
  {
    title: "Test2",
    linksArray: [{ href: "/test2", text: "smth2", disabled: true }],
  },
];

const Footer = (props: FooterProps) => {
  const {} = props;
  return (
    <footer className={styles.footer}>
      {footerArray.map((column) => (
        <div key={column.title} className={styles.footer_column}>
          <h4 className={styles.footer_column_title}>{column.title}</h4>
          {column.linksArray.map((link: footerLink) => (
            <div key={link.href} className={styles.footer_column_elem}>
              <Link href={link.href}>{link.text}</Link>
            </div>
          ))}
        </div>
      ))}
    </footer>
  );
};

export default Footer;
