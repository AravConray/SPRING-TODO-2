export class User {
  id: number;
  username: string;
  email: string;
  password: string;
  roles: string[];

  constructor(
    id: number,
    username: string,
    email: string,
    password: string,
    roles: string[] = []
  ) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  isValidPassword(hash: string): boolean {
    // Implement password hash comparison logic
    return this.password === hash;
  }
}
