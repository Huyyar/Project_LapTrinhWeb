# Project_LapTrinhWeb

This is a Web Programming project repository.

## Getting Started

### Prerequisites
- Git installed on your computer
- Basic understanding of Git commands

## Working with Git Branches

### What is a Branch?
A branch in Git is a separate line of development that allows you to work on features or fixes without affecting the main codebase.

### Basic Branch Commands

#### Creating a New Branch
To create a new branch, use:
```bash
git branch <branch-name>
```

#### Switching to a Branch
To switch to an existing branch using the newer syntax (recommended):
```bash
git switch <branch-name>
```

Or using the legacy syntax:
```bash
git checkout <branch-name>
```

#### Create and Switch to a New Branch (Recommended)
To create and switch to a new branch in one command:
```bash
git checkout -b <branch-name>
```

Or using the newer Git syntax:
```bash
git switch -c <branch-name>
```

#### Viewing All Branches
To see all branches in your repository:
```bash
git branch
```

To see both local and remote branches:
```bash
git branch -a
```

### Branch Naming Conventions
It's good practice to use descriptive branch names:
- `feature/feature-name` - for new features
- `bugfix/bug-description` - for bug fixes
- `hotfix/issue-description` - for urgent fixes
- `refactor/refactor-description` - for code refactoring

Examples:
```bash
git checkout -b feature/user-authentication
git checkout -b bugfix/login-error
git checkout -b hotfix/security-patch
```

### Common Workflow

1. **Check current branch:**
   ```bash
   git branch
   ```

2. **Create and switch to a new branch:**
   ```bash
   git checkout -b feature/my-new-feature
   ```

3. **Make your changes and commit:**
   ```bash
   git add .
   git commit -m "Add my new feature"
   ```

4. **Push your branch to remote:**
   ```bash
   git push origin feature/my-new-feature
   ```

5. **Switch back to main branch:**
   ```bash
   git checkout main
   ```

6. **Delete a local branch (after merging):**
   ```bash
   git branch -d <branch-name>
   ```
   
   For unmerged branches, use force delete:
   ```bash
   git branch -D <branch-name>
   ```

7. **Delete a remote branch:**
   ```bash
   git push origin --delete <branch-name>
   ```

### Merging Branches
To merge a branch into the current branch:
```bash
git merge <branch-name>
```

### Pulling Latest Changes
Before creating a new branch, make sure you have the latest changes:
```bash
git checkout main
git pull origin main
```

**Note:** Some repositories use `master` as the default branch instead of `main`. Check your repository's default branch name with `git branch` or `git remote show origin`.

## Contributing
When contributing to this project, please follow the branch naming conventions and create a new branch for each feature or bug fix.

## License
This project is licensed under the terms specified by the project owner.