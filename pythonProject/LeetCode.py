class Solution:
    def replaceSpace(s: str) -> str:
        res = []
        for c in s:
            if c == ' ':
                res.append("%20")
            else:
                res.append(c)
        return "".join(res)


if __name__ == '__main__':
    print("hahah")
    s = "we are people"
    print(Solution.replaceSpace(s))
