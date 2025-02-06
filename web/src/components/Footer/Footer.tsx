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
    title: "Test1",
    linksArray: [{ href: "/test", text: "smth", disabled: false }],
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
      11111
      {footerArray.map((column) => (
        <div key={column.title}>
          {column.linksArray.map((link: footerLink) => (
            <div key={link.href}>
              <Link href={link.href}>{link.text}</Link>
            </div>
          ))}
        </div>
      ))}
    </footer>
  );
};

export default Footer;
