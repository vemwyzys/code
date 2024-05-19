import { useEffect, useState } from "react";

// type schema
interface User {
  id: string;
  name: string;
  avatar: string;
}
// API
const fetchUsers = async (ids: string[]): Promise<User[]> => [];

class UserCenter {
  userCache = new Map<string, User>();

  waitList: string[] = [];

  fetchingList: string[] = [];

  callBacks: Map<string, Function[]> = new Map();

  async fetchList() {
    if (this.waitList.length === 0) return;
    this.fetchingList = [...this.fetchingList, ...this.waitList];
    fetchUsers(this.waitList).then((users) => {
      users.forEach((user) => {
        this.userCache.set(user.id, user);
        this.callBacks.get(user.id)?.forEach((cb) => cb(user));
        this.callBacks.delete(user.id);
      });
      this.fetchingList = this.fetchingList.filter(
        (id) => !this.userCache.has(id),
      );
    });
    this.waitList = [];
  }

  async fetchUser(id: string): Promise<User> {
    if (!this.waitList.includes(id) && !this.fetchingList.includes(id))
      this.waitList.push(id);
    return new Promise((resolve) => {
      this.callBacks.set(id, [...(this.callBacks.get(id) || []), resolve]);
    });
  }

  constructor() {
    setInterval(this.fetchList, 300);
  }

  async getUser(id: string) {
    if (this.userCache.has(id)) return this.userCache.get(id);
    return this.fetchUser(id);
  }
}

const useUser = (
  id: string,
): {
  user: User | null;
  loading: boolean;
} => {
  const [user, setUser] = useState<User>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const userCenter = new UserCenter();
  useEffect(() => {
    setLoading(true);
    userCenter.getUser(id).then((aUser) => {
      setUser(aUser);
      setLoading(false);
    });
  }, [id]);
  return {
    user,
    loading,
  };
};
